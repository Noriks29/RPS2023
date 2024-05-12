<template>
  <div>
  <canvas ref="chart"></canvas>
  </div>
</template>

<script>
import  Chart  from 'chart.js/auto'


export default {
  name: 'BarChart',
  props: {
        Data: {
            type: Array,
            default: function(){
                return []
            }
        },
        Data_Sort: {
            type: Array,
            default: function(){
                return []
            }
        },

    },
  data() {
    return {
      chartData: {
        labels: [  ],
        datasets: [ 
          { 
            label: 'Sort Data',
            data: [] 
          } 
        ]
      },
      chartOptions: {
        responsive: true
      }
    }
  },
  mounted (){
    console.log("gfrdgrggdd")
    try {
      this.renderChart();
    } catch (error) {
      console.log(error)
    }
    
  },
  methods:{
    renderChart() {
      let indexArray = []
      for (let index = 0; index < this.Data.length; index++) {
        indexArray.push(index);
      }
      this.chartData.labels = indexArray
      this.chartData.datasets = [
        { lable: "Data", data: this.Data},
        { lable: "Data_Sort", data: this.Data_Sort},
      ]

      const ctx = this.$refs.chart.getContext('2d');

      new Chart(ctx, {
        type: 'line',
        data: {
          labels: indexArray,
          datasets: [{
            label: 'Data', data: this.Data,
            backgroundColor: 'rgba(255, 82, 82, 0.2)', borderColor: 'rgba(255, 82, 82, 1)',
            borderWidth: 3
          },
          {
            label: 'Data_Sort', data: this.Data_Sort,
            backgroundColor: 'rgba(2, 194, 56, 0.2)', borderColor: 'rgba(2, 194, 56, 1)',
            borderWidth: 3
          }
        ]
        },
        options: {
          legend: {
            position: 'bottom'
          }
        }
      });
    }
  }
}
</script>