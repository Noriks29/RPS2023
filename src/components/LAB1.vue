<template>
  <div class="hello">
    <GetDataFromFile  @GetArray="UpdateArray"/>

    
    
       
    Data Array - {{ data_array }}
    <button v-if="!sortStart" @click="sortStart=!sortStart"> Отсортировать </button>
    <SortData v-else :dataGET="data_array"/>
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
import SortData from './SortData.vue';
export default {
  name: 'LAB1',
  components:{
    GetDataFromFile,
    SortData
  },
  data(){
    return{
      data_array: [],
      sortStart: false
    }
  },
  methods: {
      UpdateArray(array) {
        console.log(array.data)
        this.data_array = array.data
      }
    }
}
</script>

<style scoped>

</style>
