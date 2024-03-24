<template>
  <div class="hello">
    <GetDataFromFile  @GetArray="UpdateArray"/>

    
    
       
    Data Array - {{ data_array }}
    <button @click="SortDataArray(data_array)">Построить дерево</button>
    <TreeComponent :Tree="DataTree" />
    <button v-if="!sortStart" @click="GetTreeValue"> Отсортировать </button>
    <SortData v-else :dataGET="DataSort"/>
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
import SortData from './SortData.vue';
import TreeComponent from './TreeComponent.vue'
import {Tree} from '../js/tree.js'


export default {
  name: 'LAB1',
  components:{
    GetDataFromFile,
    SortData,
    TreeComponent
  },
  data(){
    return{
      data_array: [],
      sortStart: false,
      DataTree: {},
      DataSort: []
    }
  },
  methods: {
      UpdateArray(array) {
        console.log(array.data)
        this.data_array = array.data
      },
      SortDataArray(data){
        const tree = new Tree(data[0]);
        console.log(data)
        for (let element = 1; element < data.length; element++) {
          tree.SetValue(data[element])
        }
        
        this.DataTree = tree
        console.log(typeof(tree))
      },
      GetTreeValue(){
        this.sortStart = true
        let arr = this.DataTree.GetValue()
        console.log("rezult - ", arr)
        this.DataSort = arr
      }
    }
}
</script>

<style scoped>

</style>
