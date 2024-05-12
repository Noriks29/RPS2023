<template>
    <div class="GetData">
        <div>
            <button @click="Get_Data"> Запросить данные </button>
        </div>
        <div class="Datalist">
            <div class="DataItem"
                v-for="data, index in dataJson"
                :key="index"
                
            ><div @click="PostData(data)">{{ data.arrayName }}</div><button @click="DelateArray(data.arrayId, index)">X</button></div>
        </div>
    </div>
</template>

<script>
export default{
    name: "BDC",
    data(){
        return{
            dataGET: [],
            editing: false,
            dataJson: []
        }
    },
    methods:{
        PostData(select_data) { 
            this.$emit('GetArray', select_data)
        },
        async Get_Data(e){
            console.log(e)
                try {

                    const response = await fetch('http://localhost:8083/api/v1/load-array/get/all');
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    else{
                    const result = await response.json();
                    let date = new Date();
                    let datetime = date.getDate()+"."+date.getMonth()+"."+date.getFullYear()+" "+ date.getHours()+":"+ date.getMinutes()+":"+ date.getSeconds()
                    console.log(result, datetime);
                    this.dataJson = result;
                    }
                } catch (error) {
                    //this.timefetch = "Error";
                    console.error('Error during fetch:', error);
                }
        },
       async DelateArray(idArr, index){
            try {
              const response = await fetch('http://localhost:8083/api/v1/load-array/delete?arrayId='+idArr,{
                method:  'POST'
              })
              .then(response => response.json())
              .then(data => console.log(data))
                this.dataJson.splice(index, 1);
                this.Get_Data()
                console.log(response)
              
            } catch (error) {
                this.timefetch = "Error";
                console.error('Error approve:', error);
            }
        },
        DelateRow(index){
            this.dataGET.splice(index, 1);
        },
        AddRow(){
            this.dataGET.push(0)
        },
        ChangeaDta(e){
            this.dataGET[e.target.id] = e.target.value
            //console.log(e.target.value, this.dataGET[e.target.id])
        },
        Add50RandomEl(){
            function randomNumber(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            }
            
            for (let index = 0; index < 50; index++) {
                this.dataGET.push(randomNumber(1, 200) - 100)
                
            }
        }
    }
}


</script>

<style scoped lang="scss">
.Datalist{
    padding: 25px;
    .DataItem{
        display: flex;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    border-bottom: 1px solid black;

    button{
        background: none;
        margin: 5px;
    }
    }
    
}

</style>