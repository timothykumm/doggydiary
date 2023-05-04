<template>
    <div>
        <input type="file" ref=file @change=processFile() accept="image/*" capture />
        <h5 name="scanning-instruction"> Scanne unten rechts ein Dokument und ich fasse es zusammen. </h5>
        <pre name="summary-output" v-text="summary"></pre>

        <div v-if="isLoading" name="loading">
            <self-building-square-spinner
            :animation-duration="6000"
            :size="60"
            color="#007bff"
            />
            <p ref="status"></p>
        </div>
    </div>
</template>

<script>

import { ref } from 'vue';
import AskGpt from './AskGpt.vue';
import { createWorker, PSM, OEM } from 'tesseract.js';
import { SelfBuildingSquareSpinner  } from 'epic-spinners';

export default {
    name: 'FileInput',
    components: {
        SelfBuildingSquareSpinner
    },
    data() {
        return {
            summary: '',
            isLoading: false
        }
    },
    setup() {
        const file = ref(null)
        const extractedText = ref('')
        const status = ref('')

        const getFile = async () => {
            console.log("selected file", file.value.files[0])
            file.value = file.value.files[0]
        }

        const worker = createWorker({
            logger: m => status.value.innerHTML = `${m.status} : ${(m.progress * 100).toFixed(2)}%`
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
            extractedText,
            status
        }
    },
    methods: {
        async processFile() {
            await this.toggleLoadingSymbol()
            await this.getFile()
            await this.extractText()
            this.summary = await this.getGptResponse()
            this.toggleLoadingSymbol()
        },
        async getGptResponse() {
            return await AskGpt.methods.postApiRequest(this.extractedText)
        },
        async toggleLoadingSymbol() {
            this.isLoading = !this.isLoading;
        }
    }
}

</script>

<style scoped>
div {
    background-color: #32373d;
}

input {
    border-radius: 50%;
    position: absolute; /* Positioniere das Feld relativ zum nächsten positionierten Element */
    bottom: 20px; /* Abstand von unten */
    right: 20px;
    background-color: #007bff; /* Hintergrundfarbe */
    cursor: pointer;
    border: none;
    font-family: 'Helvetica Neue', sans-serif;
    width: 60px; 
    height: 60px;
    background-image: url('../assets/scan.png'); /* Setzt das  Icon */
    background-repeat: no-repeat; /* Stellt sicher, dass das Icon nicht wiederholt wird */
    background-size: contain;
    text-indent: -9999px; /* Text des Inputfeldes wird weggesetzt */
}



.self-building-square-spinner {
  display: inline-block;
  justify-content: center;
  align-items: center;
  margin-top: 0%;
}

pre[name="summary-output"] {
  font-family: "Courier New", Courier, monospace;
  font-size: 22px;
  line-height: 100%;
  padding: 5%;
  display: block;
  font-family: monospace;
  white-space: pre;
  white-space: pre-wrap;       /* Since CSS 2.1 */
  white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */
  white-space: -pre-wrap;      /* Opera 4-6 */
  white-space: -o-pre-wrap;    /* Opera 7 */
  word-wrap: break-word;
  color: #ffffff;
  background-color: #32373d;
}

pre:empty {
  visibility: hidden;
}

h5[name="scanning-instruction"] {
    padding-top: 2%;
    color: #ffffff;
}

p {
    color: #ffffff;
}

</style>
