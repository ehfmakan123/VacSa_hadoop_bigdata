<script>
import { Doughnut } from "vue-chartjs";

export default {
  extends: Doughnut,
  mounted() {
    this.renderChart(
      {
        labels: [
          "발열",
          "기침", 
          "콧물", 
          "인후통", 
          "두통",
          "증상 없음"
        ],
        datasets: [
          {
            backgroundColor: [ "#A684B7", "#DD7445", "#DE9D11", "#E0D295", "#B1D166", "#78BAA1" ],
            borderColor: "#eee", 
            hoverBorderColor: "#eee", 
            data: [21.1, 41.8, 11, 15.7, 17.2, 26.7] 
          } 
        ] 
      }, 
      { responsive: true, maintainAspectRatio: false, }
    )
  }
}
</script>
