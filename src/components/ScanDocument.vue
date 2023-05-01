<template>
    <div>
        <input type="file" ref=file @change=processFile() accept="image/*" capture />
        <p> {{ summary }} </p>
    </div>
</template>

<script>

import { ref } from 'vue'
import { createWorker, PSM, OEM } from 'tesseract.js';
import AskGpt from './AskGpt.vue';

export default {
    name: 'FileInput',
    Components: {
        AskGpt
    },
    data() {
        return {
            summary: 'Summary'
        }
    },
    setup() {
        const file = ref(null)
        const extractedText = ref('')

        const getFile = async () => {
            console.log("selected file", file.value.files[0])
            file.value = file.value.files[0]
        }

        const worker = createWorker({
            logger: m => console.log(`${m.status} : ${(m.progress * 100).toFixed(2)}%`)
        })

        const extractText = async () => {

            await worker.load()
            await worker.loadLanguage('deu')
            await worker.initialize('deu', OEM.LSTM_ONLY)

            await worker.setParameters({
                tessedit_pageseg_mode: PSM.SINGLE_BLOCK,
            })

            const { data } = await worker.recognize(file.value)
            extractedText.value = data.text
        }

        return {
            getFile,
            extractText,
            file,
            extractedText
        }
    },
    methods: {
        async processFile() {
            await this.getFile()
            await this.extractText()
            this.summary = await this.getGptResponse()
        },
         async getGptResponse() {
            AskGpt.methods.postApiRequest(this.extractedText).then(async response => {
                return response;
            })
        }
    }
}

</script>