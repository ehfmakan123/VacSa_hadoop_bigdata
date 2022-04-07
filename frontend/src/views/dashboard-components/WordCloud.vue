<template>
  <div id="app">
    <wordcloud
      :data="defaultWords"
      nameKey="name"
      valueKey="value"
      :color="myColors"
      :showTooltip="true"
      :wordClick="wordClickHandler"
    >
    </wordcloud>
  </div>
</template>

<script>
import wordcloud from "vue-wordcloud";
import { getWord } from "@/api/wordCount.js";
export default {
  name: "app",
  components: {
    wordcloud,
  },
  methods: {
    wordClickHandler(name, value, vm) {
      console.log("wordClickHandler", name, value, vm);
    },
  },
  data() {
    return {
      myColors: ["#1f77b4", "#629fc9", "#94bedb", "#c9e0ef"],
      defaultWords: [],
    };
  },
  created() {
    getWord(
      (response) => {
        for (var i = 0; i < response.data.length; i++) {
          var data = {
            name: response.data[i].word,
            value: response.data[i].count,
          };
          this.defaultWords.push(data);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>
