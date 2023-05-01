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
            return await AskGpt.methods.postApiRequest(this.extractedText)
        }
    }
}

</script>

<style scoped>
input {
    border-radius: 50%;
    position: absolute; /* Positioniere das Feld relativ zum nächsten positionierten Element */
    bottom: 20px; /* Abstand von unten */
    right: 20px;
    background-color: #007bff; /* Hintergrundfarbe */
    color: #fff;
    cursor: pointer;
    border: none;
    font-family: 'Helvetica Neue', sans-serif;
    width: 60px; 
    height: 60px;
    background-image: url('../assets/scan.png'); /* Setzt das  Icon */
    background-repeat: no-repeat; /* Stellt sicher, dass das Icon nicht wiederholt wird */
    background-size: contain;
    text-indent: -9999px; /* Text des Inputfeldes wird weggesetzt */
}</style>
