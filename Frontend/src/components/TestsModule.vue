<template>
    <div class="GetData">
        <div>
            <button @click="Test1"> Тест 1 </button>
            <button @click="Test2"> Тест 2 </button>
            <button @click="Test3"> Тест 3 </button>
            <button @click="Test4"> Тест 4 </button>
            <button @click="Test5"> Тест 5 </button>
        </div>
        <div>
            
        </div>
    </div>
</template>

<script>

import {CreateTree,
   GetValue,
    CreateArray } from '../js/sort.js'
export default{
    name: "TestsModule",
    data(){
        return{
            dataJson: []
        }
    },
    methods:{
        async SelectALL(){
            try {
                    const response = await fetch('http://localhost:8083/api/v1/load-array-test/get/all');
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    else{
                    const result = await response.json();
                    return result
                    }
                } catch (error) {
                    console.error('Error during fetch:', error);
                }
           
            

        },
        async DelateArray(idArr){
            try {
              await fetch('http://localhost:8083/api/v1/load-array-test/delete?arrayId='+idArr,{
                method:  'POST'
              })
              .then(response => response.json())
              //.then(data => console.log(data))
                //console.log(response)
              
            } catch (error) {
                this.timefetch = "Error";
                console.error('Error approve:', error);
            }
        },
        async AddRow(ArrayList){
            let correct_add = 0
            for (let i = 0; i < ArrayList.length; i++) {
                ArrayList[i];
                try {
                    await fetch('http://localhost:8083/api/v1/load-array-test/add',{
                    method:  'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(ArrayList[i])
                    })
                    .then(response => response.json())
                    correct_add++
                    } catch (error) {
                        this.timefetch = "Error";
                        console.error('Error approve:', error);
                    }
            }
            console.log("Коректно доабленые записи - ",correct_add + " из " + ArrayList.length)
            
        },
        async Test1(){
            console.log("Старт теста 1")
            
            let date1 = new Date();
            await this.AddRow(this.AddArray(100))
            let date2 = new Date()
            console.log("Время выполнения теста - ", date2.getTime() - date1.getTime(), "мс")
        },
        async Test2(){
            console.log("Старт теста 2")
            let date1 = new Date();
            await this.AddRow(this.AddArray(1000))
            let date2 = new Date()
            console.log("Время выполнения теста - ", date2.getTime() - date1.getTime(), "мс")
        },
        async Test3(){
            console.log("Старт теста 3")
            let date1 = new Date();
            await this.AddRow(this.AddArray(10000))
            let date2 = new Date()
            console.log("Время выполнения теста - ", date2.getTime() - date1.getTime(), "мс")
        },
        async Test4(){
            console.log("Старт теста 4")
            let date1 = new Date();
            const arr = await this.SelectALL()
            for (let i = 0; i < arr.length; i++) {
                await GetValue(CreateTree(arr[i].arrayData));
                //console.log(arr[i].arrayId)
            }
            let date2 = new Date()
            console.log("Время выполнения теста - ", date2.getTime() - date1.getTime(), "мс")
            console.log("Отсортиравано - " + arr.length + " записей")
        },
        async Test5(){
            console.log("Старт теста 5")
            let date1 = new Date();
            const arr = await this.SelectALL()
            for (let i = 0; i < arr.length; i++) {
               await this.DelateArray(arr[i].arrayId)
            }
            let date2 = new Date()

            console.log("Время выполнения теста - ", date2.getTime() - date1.getTime(), "мс")
            console.log("Удалено - " + arr.length + " записей")
        },
        AddRandomEl(){
            function randomNumber(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            }
            let arr = []
            for (let index = 0; index < randomNumber(6, 15); index++) {
                arr.push(randomNumber(1, 200) - 100)
            }
            return arr
        },
        AddArray(size){
            let arrayList = []
            for (let index = 0; index < size; index++) {
                let array = CreateArray();
                array.arrayData = this.AddRandomEl()
                array.arrayName = "Test"+index
                arrayList.push(array)
            }
            console.log(arrayList)
            return arrayList
        }
    }
}


</script>
