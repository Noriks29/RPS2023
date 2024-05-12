<template>
  <div>
    <TestsModule v-if="true"/> <!-- Подключение тестов -->
    <div class="header" @click="ChangePage"> <!-- меню -->
      <div id="0">Главная</div>
      <div  id="1" >Показать дерево</div>
      <div  id="2" >Построить график</div>
    </div>
  <div class="menuflex">
    <div v-if="DisplayPage[0]" class="BD_div">
      <BDComponent  @GetArray="UpdateArray"/> <!-- компонент работы основных запросов к базе -->
      <div v-if="Array_data.arrayData.length > 0" class="BD_div_mini"><!-- Меню настройки параметров массива -->
        <div>Название массива 
        <input id="ArraynameInput" type="text" :value="Array_data.arrayName">
      </div>
        <div><input type="checkbox" id="PostSortArray" name="interest" value="coding" />
        <label for="PostSortArray">Сохранить сортированный массив</label></div>
        <button @click="PostArray" > Сохранить массив в базу</button>
      </div>
    </div>
    <div class="GetDataDiv"  v-if="DisplayPage[0]">
      Работа с массивом
      <GetDataFromFile  @GetArray="UpdateArray" :data_array="Array_data"/> <!-- компонент работы с файлами и обработки массива -->
    </div>
    <div class="TreeDiv"  v-if="DisplayPage[1]">
      <!--Data Array - {{ Array_data.arrayData }}-->
      <TreeComponent :Tree="DataTree" /> <!-- Компонент построения дерева -->
    </div>
    <div>
      <SortData v-if="Array_data.arrayData.length > 0" :dataGET="DataSort"/> <!--компонент отображения отсортированнного массива  -->
    </div>
    
  </div>
  <div  v-if="DisplayPage[2]">
    <GrafComponent :Data="Array_data.arrayData" :Data_Sort="DataSort"/> <!-- компонент построения графика -->
  </div>
  </div>
</template>

<script>
import GetDataFromFile from './GetDataFromFile.vue';
import SortData from './SortData.vue';
import TreeComponent from './TreeComponent.vue'
import GrafComponent from './GrafComponent.vue'
import BDComponent from './BDComponent.vue'
import TestsModule from './TestsModule.vue'
import { CreateTree , GetValue} from '../js/sort'


export default {
  name: 'LAB1',
  components:{
    GetDataFromFile,
    SortData,
    TreeComponent,
    GrafComponent,
    BDComponent,
    TestsModule
  },
  data(){
    return{
      data_array: [],
      Array_data: {   // базовый обьект
        arrayData: [],
        arrayName: "Без имени",
        arrayId: undefined
      },
      DataTree: {},
      DataSort: [],
      DisplayPage: [true, false, false], // параметры меню
      ShowGraph:false
    }
  },
  methods: {
    ChangePage(target){ // изменение рабочей области 
      
      this.DisplayPage[0] = false
      this.DisplayPage[1] = false
      this.DisplayPage[2] = false
      this.DisplayPage[target.target.id] = true
      console.log( this.DisplayPage)
    },
      UpdateArray(arr_data) { // обновление рабочего массива
        console.log("this.Array_data-- ",arr_data)
        this.Array_data = arr_data
        this.SortDataArray(this.Array_data.arrayData)
      },
      SortDataArray(data){ //метод построения дерева
        this.DataTree = CreateTree(data)
        this.GetTreeValue()
      },
      GetTreeValue(){ // метод чтения дерева
        this.DataSort = GetValue(this.DataTree)
      },
      PostArray(){ //метод сохраненния массива в базу
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
          //console.log("uppdate", this.Array_data.arrayId)
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
                //this.timefetch = "Error";
                console.error('Error approve:', error);
          }
        }
        else{
          //console.log("add", this.Array_data)
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
                //this.timefetch = "Error";
                console.error('Error approve:', error);
          }
        }

      }
    }
}
</script>

<style lang="scss" >
button{
  background: none;
    padding: 4px;
    border-radius: 5px;
}
.header{
  display: flex;
    flex-direction: row;
    justify-content: space-around;
    position: fixed;
    width: 100vw;
    top: 0px;
    border-bottom: 2px solid black;
    left: 0px;
    div{
      padding: 15px;
    flex: 1;
    font-size: 20px;
    }
}
.BD_div{
  border: 1px solid black;
    padding: 15px;
    flex: 1;
    max-width: 20%;
}
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
.BD_div_mini{
    display: flex;
    flex-direction: column;
    align-items: center;
    input{
      margin: 10px;
    }
    }
</style>
