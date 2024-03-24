<template>
    <div class="SortData">
        <div class="">
            {{ Tree.value }}
        </div>
        <div class="kidTreeFlex">
            <div >
                <TreeComponent v-if="Tree.leftTree != undefined" :Tree="Tree.leftTree"/>
            </div>
            <div>
                <TreeComponent v-if="Tree.rightTree != undefined" :Tree="Tree.rightTree"/>
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name: "TreeComponent",
    props: {
        Tree: {
            type: Object,
        },
    }
    

    
}
</script>

<style scoped>

.SortData{

    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
    border:  1px solid black;

    .kidTreeFlex{
        display: flex;
        margin-top: 10px;
        flex-direction: row;
        justify-content: center;
        align-items: flex-start;
        width: 100%;
        div{
            flex: 1;
        }
    }

}
</style>