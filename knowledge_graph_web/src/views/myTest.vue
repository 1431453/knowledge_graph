<template>
  <div class="myDiv">
    <div id="viz"></div>
<!--    Cypher query: <textarea rows="4" cols=50 id="cypher"></textarea><br>-->
<!--    <input type="submit" value="Submit" id="reload" @click="submit">-->
<!--    <input type="submit" value="Stabilize" id="stabilize" @click="stabilize">-->
  </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      viz: {} //定义一个viz对象
    }
  },
  mounted() { this.draw() }, //渲染
  methods: {
    // submit() {
    //   if (cypher.length > 3) {
    //     this.viz.renderWithCypher(cypher);
    //   } else {
    //     console.log("reload");
    //     this.viz.reload();
    //
    //   }
    // },
    stabilize() {
      this.viz.stabilize();
    },
    draw() {
      let config = {
        container_id: "viz",
        server_url: "bolt://10.99.104.191:7687",
        server_user: "neo4j",
        server_password: "123456",
        labels: {
          "divemedical":{
            caption: "name",
            size: "degree"
          }
        },
        // relationships: {
        //   "包括": {
        //     thickness: "weight",
        //     caption: true,
        //     color: "#777",
        //     curved: false,
        //     arrows:'from',
        //   },
        //   "治疗方法": {
        //     thickness: "weight",
        //     caption: true,
        //     color: "#ad1a66",
        //     curved: false
        //   }
        // },
        initial_cypher:"match (n:"+this.label+")-[r]->(n1) return n1,n,r",
        vis: {
          nodes: {
            shape: "circle", // 设置节点形状为圆形
            color: "#ad1a66", // 设置节点颜色为浅红色
            size: 500, // 设置节点大小为500
            label: { // 配置节点标签字体样式
              color: "#ffffff",
              size: 14,
              // 设置标签名称为节点的name属性
              field: "name"
            }
          },
          edges: {
            color: "#777", // 设置关系颜色为灰色
            thickness: "weight", // 设置关系粗细为weight属性值
            length: 100, // 设置关系长度为100
            label: { // 配置关系标签字体样式
              color: "#777",
              size: 12,
              // 在关系上展示关系权重的值
              field: "weight"
            }
          },
          physics: {
            barnesHut: {
              gravitationalConstant: -15000,
              springLength: 400
            },
            minVelocity: 0.75
          }
        }
      };
      // eslint-disable-next-line no-undef
      this.viz = new NeoVis.default(config);
      this.viz.render();
      console.log(this.viz);
    },
  },
}
</script>
<style lang="less" scoped>
.myDiv {
  width: 800px;
  height: 800px;
}
textarea {
  border: 1px solid lightgray;
  margin: 5px;
  border-radius: 5px;
}
#viz {
  width: 100%;
  height: 80%;
  border: 1px solid #f1f3f4;
  font: 22pt arial;
}
input {
  border: 1px solid #ccc;
}
</style>