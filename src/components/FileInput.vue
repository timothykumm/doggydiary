<template>
    <div>
        <input type="file" ref=file @change="getFile()" accept="image/*" capture />
    </div>
</template>

<script>

import { defineComponent, ref } from 'vue'
//import { createWorker, PSM, OEM } from 'tesseract.js';

export default defineComponent({
    name: 'FileInput',
    setup() {
        const file = ref(null)
        const getFile = async () => {
            console.log("selected file", file.value.files[0])
        }
        return {
          getFile,
          file
       }
    },
})

</script>