<script>
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  mounted() {
    this.renderChart(
      {
        labels: [
          "두통",
          "근육통",
          "어지러움",
          "흉통",
          "메스꺼움",
          "오한",
          "구토"
        ],
        datasets: [
          {
            label: "단위: 건",
            backgroundColor: "#c9c9c9",
            data: [110187, 106192, 67350, 63002, 52942, 40137, 23355],
          }
        ]
      },
      { responsive: true, maintainAspectRatio: false, scales: {
              xAxes: [{
                  barThickness: 30,
              }]
          } }
    );
  }
};
</script>
