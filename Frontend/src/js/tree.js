export class Tree{
    constructor(value){
        this.leftTree = undefined;
        this.rightTree = undefined;
        this.value = value
    }


    SetValue(value){
        if(value <= this.value){
            if(this.leftTree === undefined)
                this.leftTree = new Tree(value)
            else
                this.leftTree.SetValue(value)
        }
        else{
            if(this.rightTree === undefined)
                this.rightTree = new Tree(value)
            else 
                this.rightTree.SetValue(value)
        }
    }
    GetValue(){
        let array = []
        if(this.leftTree !== undefined)
            array = array.concat(this.leftTree.GetValue())

        array = array.concat(this.value)

        if(this.rightTree !== undefined)
            array = array.concat(this.rightTree.GetValue())
        //console.log(array)
        return array

    }
}