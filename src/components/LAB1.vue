<template>
  <div class="hello">
    <GetDataFromFile  @GetArray="UpdateArray"/>
    <div v-for="data, index in data_array"
      :key="index"
    >
       
    </div>
    Data Array - {{ data_array }}
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
export default {
  name: 'LAB1',
  components:{
    GetDataFromFile
  },
  props: {
    msg: String
  },
  data(){
    return{
      data_array: [],
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
