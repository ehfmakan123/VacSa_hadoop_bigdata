// module.exports = {
//   Proxy: {
//     "/": {
//       target: "http://j6a402.p.ssafy.io:8080/api",
//       pathRewrite: { "^/": "" },
//       changeOrigin: true,
//     },
//   },
// };
module.exports = {
  devServer: {
    proxy: {
      "/": {
        target: "https://nip.kdca.go.kr/",
        pathRewrite: { "^/": "" },
        changeOrigin: true,
        secure: false,
      },
    },
  },
};
// http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=Mo7exllHksroP%2BskN4M7XGw%2FLNhgZ3HNCV%2BZkPLkAJvPpGb21ckO%2F6KynGVAf4dMAbe%2F6a7COLv2g252FeE%2FFw%3D%3D&pageNo=1&numOfRows=1&startCreateDt=20220310
