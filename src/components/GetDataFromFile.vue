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
            <ul>
                <li v-for="data, index in dataGET"
                    :key="index"
                >
                    {{ index }} 
                    <input type="number" @change="ChangeData" :id="index" :value="data" :class="editing ? 'active' : ''">
                    <button v-if="editing" @click="DelateRow(index)">-</button>
                </li>
                <li v-if="editing" @click="AddRow"> Добавить</li>
            </ul>
        </div>
        <div>
            <button v-if="!editing" @click="editing = !editing">
                Редактировать
            </button>
            <div v-else>
                <button @click="Add50RandomEl">
                    Добавить 50 случайных элементов от -100 до 100
                </button>
                <button  @click="SaveChange">
                    Сохранить
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
            dataGET: [],
            editing: false
        }
    },
    methods:{
        PostData() {
            this.$emit('GetArray', {
            data: this.dataGET
            })
        },
        Get_Data(e){
            const reader = new FileReader();
            reader.readAsText(e.target.files[0]);
            reader.addEventListener('load', (dataF) => {
                dataF.target.result.split(',').forEach(element => {
                    this.dataGET.push(Number(element))
                });
                //console.log(this.dataGET[0])
                this.PostData()
            });
            
        },
        SaveChange(){
            this.editing = false
            this.PostData()
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

<style scoped>

.GetData{

    max-height: 70vh;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;

    .divList{
        display: inline-block;
        max-height: 70vh;
        overflow-y: auto;

        ul{
            list-style-type: none;
            
            li{
                padding: 10px;
                border: 1px solid black;
                border-radius: 14px;
                background-color: darkgray;

                input{
                    
                    pointer-events:none;
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
}
</style>