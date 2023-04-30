<template>
    <HelloWorld msg="Hello World"/>
    <FileInput></FileInput>
</template>

<script>
import HelloWorld from '../components/HelloWorld.vue'
import FileInput from '../components/FileInput.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    FileInput
  }
}
</script>