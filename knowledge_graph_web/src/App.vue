<template>
  <myIndex/>
</template>

<script>
import myIndex from './components/myIndex.vue';


export default {
  name: 'App',
  components: {
    myIndex
},
  created() {
    if (this.$router.path !== "/three-search-content")
      this.$router.replace("three-search-content")
  }

}
</script>

<style>
</style>
