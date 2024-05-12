<template>
  <div>
  <div class="menuflex">
    <div>
      <BDComponent  @GetArray="UpdateArray"/>
      <div v-if="Array_data.arrayData.length > 0">
        <button @click="PostArray" > Сохранить массив в базу</button>
        <input id="ArraynameInput" type="text" :value="Array_data.arrayName">
        <input type="checkbox" id="PostSortArray" name="interest" value="coding" />
        <label for="PostSortArray">Сохранить сортированный массив</label>
      </div>
    </div>
    <div class="GetDataDiv">
      <GetDataFromFile  @GetArray="UpdateArray" :data_array="Array_data"/>
    </div>
    <div class="TreeDiv">
      Data Array - {{ Array_data.arrayData }}
      <TreeComponent :Tree="DataTree" />
    </div>
    <div>
      <SortData v-if="Array_data.arrayData.length > 0" :dataGET="DataSort"/>
    </div>
    
  </div>
  <div>
    <button @click="ShowGraph = !ShowGraph">Отрисовать График</button>
    <GrafComponent v-if="ShowGraph" :Data="Array_data.arrayData" :Data_Sort="DataSort"/>
  </div>
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
import SortData from './SortData.vue';
import TreeComponent from './TreeComponent.vue'
import {Tree} from '../js/tree.js'
import GrafComponent from './GrafComponent.vue'
import BDComponent from './BDComponent.vue'


export default {
  name: 'LAB1',
  components:{
    GetDataFromFile,
    SortData,
    TreeComponent,
    GrafComponent,
    BDComponent
  },
  data(){
    return{
      data_array: [],
      Array_data: {
        arrayData: [],
        arrayName: "Без имени",
        arrayId: undefined
      },
      sortStart: false,
      DataTree: {},
      DataSort: [],
      ShowGraph:false
    }
  },
  methods: {
      UpdateArray(arr_data) {
        console.log("this.Array_data-- ",arr_data)
        this.Array_data = arr_data
        this.SortDataArray(this.Array_data.arrayData)
      },
      SortDataArray(data){
        const tree = new Tree(data[0]);
        console.log(data)
        for (let element = 1; element < data.length; element++) {
          tree.SetValue(data[element])
        }
        
        this.DataTree = tree
        this.GetTreeValue()
        console.log(typeof(tree))
      },
      GetTreeValue(){
        this.sortStart = true
        let arr = this.DataTree.GetValue()
        console.log("rezult - ", arr)
        this.DataSort = arr
      },
      PostArray(){
        console.log(document.getElementById("PostSortArray").checked)
        if(document.getElementById("PostSortArray").checked)
        {
          this.Array_data.arrayData_sort = this.DataSort
        }
        else
        {
          this.Array_data.arrayData_sort = []
        }
        if(this.Array_data.arrayId != undefined && this.Array_data.arrayName == document.getElementById("ArraynameInput").value)
        {
          console.log("uppdate", this.Array_data.arrayId)
          this.Array_data.arrayName = document.getElementById("ArraynameInput").value
          try {
              fetch('http://localhost:8083/api/v1/load-array/update?sortedArrayId='+this.Array_data.arrayId,{
              method:  'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(this.Array_data)
              })
              .then(response => response.json())
              .then(data => console.log(data))
            } catch (error) {
                this.timefetch = "Error";
                console.error('Error approve:', error);
          }
        }
        else{
          console.log("add", this.Array_data)
          this.Array_data.arrayName = document.getElementById("ArraynameInput").value
          try {
              fetch('http://localhost:8083/api/v1/load-array/add',{
              method:  'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(this.Array_data)
              })
              .then(response => response.json())
              .then(data => console.log(data))
            } catch (error) {
                this.timefetch = "Error";
                console.error('Error approve:', error);
          }
        }
        console.log("333")
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
    overflow: auto;
    flex: 2
  }
}
</style>
