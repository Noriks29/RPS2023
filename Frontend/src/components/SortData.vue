<template>
    <div class="SortData">
        Отсортированный массив
        <div class="divList">
            <div v-for="data, index in dataGET"
                :key="index"
            >
                {{ data }}
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name: "SortData",
    props: {
        dataGET: {
            type: Array,
            default: function(){
                return []
            }
        },
    },

    
}
</script>

<style scoped>

.SortData{

    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
    .divList{
        display: flex;
        margin: 20px;
        flex-wrap: wrap;
        div{
            padding: 10px;
            margin-top: 3px;
            border: 1px solid black;
            border-radius: 14px;
            background-color: rgb(72, 145, 96);
        }
    }
}

</style>