import { Tree } from "./tree";


function CreateTree(data){
    const tree = new Tree(data[0]);
    //console.log(data)
    for (let element = 1; element < data.length; element++) {
      tree.SetValue(data[element])
    }
    return tree
}
function GetValue(data){
    return data.GetValue()
}
function CreateArray(){
    return { 
        arrayName: '',
        arrayData: [],
        arrayData_sort: [],
        arrayId: undefined
     }
}

export {
    CreateTree,
    GetValue,
    CreateArray
}