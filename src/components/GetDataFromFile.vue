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
            <button v-else @click="SaveChange">
                Сохранить
            </button>
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
        ChangeData(e){
            this.dataGET[e.target.id] = e.target.value
            //console.log(e.target.value, this.dataGET[e.target.id])
        },
    }
}


</script>

<style scoped>

.GetData{

    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
    .divList{
        display: inline-block;

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