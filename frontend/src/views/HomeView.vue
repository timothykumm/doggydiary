<template>
  <body>
    <NavigationBar name="DoggyDiary"></NavigationBar>
    <ScanDocument></ScanDocument>
  </body>
</template>

<script>
import NavigationBar from '@/components/NavigationBar.vue';
import ScanDocument from '@/components/ScanDocument.vue';

export default {
  name: 'HomeView',
  components: {
    NavigationBar,
    ScanDocument
}
}
</script>