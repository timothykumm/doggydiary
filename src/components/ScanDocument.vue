<template>
    <div>
        <input type="file" ref=file @change="getFile()" accept="image/*" capture />
    </div>
</template>

<script>

import { defineComponent, ref } from 'vue'
import { createWorker, PSM, OEM } from 'tesseract.js';

export default defineComponent({
    name: 'FileInput',
    setup() {
        const file = ref(null)

        const getFile = async () => {
            console.log("selected file", file.value.files[0])
            file.value = file.value.files[0]
            recognize()
        }

        const worker = createWorker({
            logger: m => console.log(`${m.status} : ${(m.progress * 100).toFixed(2)}%`)
        })

        const recognize = async () => {

            await worker.load()
            await worker.loadLanguage('deu')
            await worker.initialize('deu', OEM.LSTM_ONLY)

            await worker.setParameters({
                tessedit_pageseg_mode: PSM.SINGLE_BLOCK,
            })

            const { data } = await worker.recognize(file.value)
            console.log(data.text)
        }

        return {
            getFile,
            file
        }
    },
})

</script>