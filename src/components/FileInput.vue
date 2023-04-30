<template>
    <div>
        <input type="file" @change="getFile" accept="image/*" capture />
    </div>
</template>

<script>

import { defineComponent, ref } from 'vue'
//import { createWorker, PSM, OEM } from 'tesseract.js';

export default defineComponent({
    name: 'FileInput',
    data() {
        return {
            getFile: null
        }
    },
    methods: {
        loadFile() {
            const src = ref('')
            //Init reader
            const reader = new FileReader()
            reader.onload = () => {
                src.value = reader.result
            }
            reader.readAsDataURL(this.getFile.target.files[0])
        }
    }
})

</script>