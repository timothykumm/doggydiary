<template>
    <HelloWorld msg="Hello World"/>
    <ScanDocument></ScanDocument>
</template>

<script>
import HelloWorld from '../components/HelloWorld.vue'
import ScanDocument from '@/components/ScanDocument.vue';

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    ScanDocument
}
}
</script>