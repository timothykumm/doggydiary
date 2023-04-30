<template>
    <div>
        <input type="file" ref=file @change="getFile()" accept="image/*" capture />
        <h1> {{ "Summary: " + summary }} </h1>
    </div>
</template>

<script>

import { ref } from 'vue'
import { createWorker, PSM, OEM } from 'tesseract.js';

export default {
    name: 'FileInput',
    Components: {

    },
    setup() {
        const file = ref(null)
        const summary = ref('')

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
            requestOutput("Das folgende Dokument ist von einem Hund. Schreibe eine kurze Überschrift nach <Überschrift> fasse das Dokument in <Inhalt> zusammen und falls es ein ärztliches Dokument ist, analysiere die Werte unter <Analyse> " + data.text)
        }

        function requestOutput(input) {
            const endpoint = 'https://api.openai.com/v1/chat/completions'

            const myHeaders = new Headers()
            const openAiKey = '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'
            myHeaders.append('Authorization', 'Bearer ' + openAiKey)
            myHeaders.append('Content-Type', 'application/json')

            const raw = JSON.stringify({
                model: "gpt-3.5-turbo",
                messages: [{ "role": "user", "content": input }],
                temperature: 0.7
            })

            const requestOptions = {
                method: 'POST',
                body: raw,
                headers: myHeaders,
                redirect: 'follow'
            }

            requestFetch(endpoint, requestOptions)
        }

        async function requestFetch(endpoint, requestOptions) {
            fetch(endpoint, requestOptions)
                .then(response => response.text())
                .then(async result => {
                    console.log(result)
                    const parsedObj = JSON.parse(result);
                    const content = parsedObj.choices[0].message.content;
                    summary.value = content;
                })
                .catch(error => console.log('error', error))
            //this.$router.push('/')
        }

        return {
            getFile,
            file,
            summary
        }
    }
}

</script>