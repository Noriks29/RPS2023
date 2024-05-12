<template>
    <div class="GetData">
        <div>
        <input 
            type="file"
            accept=".dataArray" 
            @change="Get_Data"
            />
        </div>
        <div class="divList">
                <div v-for="data, index in data_array.arrayData"
                    :key="index"
                >
                    {{ index }} 
                    <input type="number" @change="ChangeData" :id="index" :value="data" :class="editing ? 'active' : ''">
                    <button v-if="editing" @click="DelateRow(index)">-</button>
            </div>
        </div>
        <div>
            <button v-if="!editing" @click="editing = !editing">
                Редактировать
            </button>
            <div v-else>
                <div v-if="editing" @click="AddRow"> <button>Добавить</button></div>
                <button @click="Add50RandomEl">
                    Добавить 50 случайных элементов от -100 до 100
                </button>
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name: "GetFile",
    data(){
        return{
            dataGET: {},
            editing: false
        }
    },
    props: {
        data_array: {
            type: Object,
        },
    },
    methods:{
        PostData() {
            this.$emit('GetArray', this.dataGET)
        },
        Get_Data(e){
            this.dataGET = this.data_array
            const reader = new FileReader();
            reader.readAsText(e.target.files[0]);
            reader.addEventListener('load', (dataF) => {
                dataF.target.result.split(',').forEach(element => {
                    this.dataGET.arrayData.push(Number(element))
                });
                this.PostData()
            });
            
        },
        DelateRow(index){
            this.dataGET = this.data_array
            this.dataGET.arrayData.splice(index, 1);
            this.PostData()
        },
        AddRow(){
            this.dataGET = this.data_array
            this.dataGET.arrayData.push(0)
            this.PostData()
        },
        ChangeData(e){
            this.dataGET = this.data_array
            this.dataGET.arrayData[e.target.id] = Number(e.target.value) 
            this.PostData()
        },
        Add50RandomEl(){
            this.dataGET = this.data_array
            function randomNumber(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            }
            
            for (let index = 0; index < 50; index++) {
                this.dataGET.arrayData.push(randomNumber(1, 200) - 100)
            }
            this.PostData()
        }
    }
}


</script>

<style scoped>

.GetData{

    max-height: 70vh;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;

    .divList{
        display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    margin: 20px;
        max-height: 70vh;
        overflow-y: auto;
        margin-bottom: 15px;
        

            
            div{
                display: flex;
                padding: 6px;
                border: 1px solid black;
                border-radius: 14px;
                background-color: darkgray;

                input{
                    width: 60px;
                    pointer-events:none;
                    margin: 0px 5px 0px 5px;
                    background: none;
                    border: none;

                    &.active{
                        pointer-events: all;
                        border: 1px solid black;
                    }
                }
            }
    }
}
</style>