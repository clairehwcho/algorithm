// Predict the output
var fruit1 = "apples";
var fruit2 = "oranges";

fruit2 = fruit1;

console.log("-----------")
console.log(fruit2 + " and " + fruit1);
// OUTPUT: apples and apples


// Predict the output
var fruit1 = "apples";
var fruit2 = "oranges";

var temp = fruit1;
fruit1 = fruit2;
fruit2 = temp;

console.log("-----------")
console.log(fruit2 + " and " + fruit1);
// OUTPUT: apples and oranges


// Predict the output
var start = 0;
var end = 12;

while (start < end) {
    console.log("start: " + start + ", end: " + end);
    start += 2;
    end -= 2;
}
/*
OUTPUT:
start: 0, end: 12
start: 2, end: 10
start: 4, end 8
*/


// Reverse an array
const arr1 = ["a", "b", "c", "d", "e"];
const arr2 = ["a", "b", "c", "d", "e", "f"];

function reverseArr (arr) {
    let temp = "";
    for (let startIdx = 0; startIdx < arr.length / 2; startIdx++) {
        endIdx = arr.length - 1 - startIdx;
        temp = arr[startIdx];
        arr[startIdx] = arr[endIdx];
        arr[endIdx] = temp;
    }
    return arr;
}

console.log("----- reverseArr -----");
console.log(reverseArr(arr1));
console.log(reverseArr(arr2));