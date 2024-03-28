<template>
  <div>
  <div class="menuflex">
    <div class="GetDataDiv">
      <GetDataFromFile  @GetArray="UpdateArray"/>
    </div>
    <div class="TreeDiv">
      Data Array - {{ data_array }}
      <button @click="SortDataArray(data_array)">Построить дерево</button>
      <TreeComponent :Tree="DataTree" />
    </div>
    <div>
      <button v-if="!sortStart" @click="GetTreeValue"> Отсортировать </button>
      <SortData v-else :dataGET="DataSort"/>
    </div>
    
  </div>
  <div>
    <button @click="ShowGraph = true">Отрисовать График</button>
    <GrafComponent v-if="ShowGraph" :Data="data_array" :Data_Sort="DataSort"/>
  </div>
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
import SortData from './SortData.vue';
import TreeComponent from './TreeComponent.vue'
import {Tree} from '../js/tree.js'
import GrafComponent from './GrafComponent.vue'


export default {
  name: 'LAB1',
  components:{
    GetDataFromFile,
    SortData,
    TreeComponent,
    GrafComponent
  },
  data(){
    return{
      data_array: [],
      sortStart: false,
      DataTree: {},
      DataSort: [],
      ShowGraph:false
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

<style lang="scss" scoped>
.menuflex{
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 10px;
  div{
    flex: 1;
  }
  .GetDataDiv{
    flex: 1;
  }
  .TreeDiv{
    flex: 2
  }
}
</style>
