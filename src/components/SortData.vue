<template>
    <div class="SortData">
        <div class="divList">
            <ul>
                <li v-for="data, index in dataGET"
                    :key="index"
                >
                {{ data }}
                </li>
            </ul>

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
        display: inline-block;

        ul{
            list-style-type: none;
            
            li{
                padding: 10px;
                border: 1px solid black;
                border-radius: 14px;
                background-color: rgb(72, 145, 96);
            }
        }
    }
}
</style>