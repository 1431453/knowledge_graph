<template>
  <div>
    <a-spin :spinning="spin" style="background-color:rgb(255,255,255,0.3);height: 100vh;width: 100vh">
    <a-tabs
        v-model:activeKey="activeKey"
        :tab-position="mode"
        :style="{ minHeight: '200px' ,marginTop:'-10px'}"
        @change="changeTab"
    >
      <a-tab-pane v-for="item in themes" :key="item.id" :tab="item.title" >
        <div id="viz" style="height: 800px"></div>
      </a-tab-pane>
    </a-tabs>
    </a-spin>
  </div>
</template>

<script>
import { ref} from "vue";
export default {
  name: "graphShow",
  setup(){
    const mode = ref('top');
    let viz = null;
    const spin = ref(false);
    const  themes= ref([
      {id:'001',title:'舰艇防火灭火',name:'shipfhmh',color:'red'},
      {id:'002',title:'舰艇技术装备损管',name:'shiptecdm',color:'blue'},
      {id:'003',title:'舰艇抗沉',name:'shipresunk',color:'orange'},
      {id:'004',title:'舰艇损管组织与训练',name:'shipdmorgandwork',color:'green'},
      {id:'005',title:'潜水疾病',name:'divemedical',color:'cyan'},
      {id:'006',title:'潜水技术与基础训练',name:'divetecwork',color:'pink'},
      {id:'007',title:'潜水相关与潜水器具',name:'divereandeq',color:'blue'},
      {id:'008',title:'潜水作业与组织实施',name:'diveorganduse',color:'purple'},
      {id:'09',title:'潜水物理知识',name:'divephysics',color:'red'},
      {id:'010',title:'人',name:'person',color:'cyan'},
      {id:'011',title:'化学',name:'chemistry',color:'green'},
      {id:'012',title:'位置',name:'position',color:'pink'},
      {id:'013',title:'身体',name:'body',color:'pink'},
      {id:'014',title:'其它',name:'others',color:'pink'},
    ]);
    const activeKey = ref(themes.value[3].id);
    const nameData = '潜水疾病';
    const label = ref('shipfhmh');
    return{
      spin,
      viz,
      label,
      mode,
      activeKey,
      themes,
      nameData,
    }
  },
  methods:{
    draw() {
      let config = {
        container_id: "viz",
        server_url: "bolt://10.99.104.191:7687",
        server_user: "neo4j",
        server_password: "123456",
        labels: {
            'diveorganduse':{
              caption: "name",
              size: "degree"
            },
          'divereandeq':{
            caption: "name",
            size: "degree"
          },
          'shipfhmh':{
            caption: "name",
            size: "degree"
          },
          'shiptecdm':{
            caption: "name",
            size: "degree",
          },
          "shipdmorgandwork":{
            caption: "name",
            size: "degree",
          },
          'shipresunk':{
            caption: "name",
            size: "degree",
          }
        },
        color:'pink',
        relationships: {
          '使用': {
            thickness: "weight",
            caption: true,
            color: "#ee5959",
            curved: false,
            arrows:'from',
          },
          "治疗方法": {
            thickness: "weight",
            caption: true,
            color: "#ad1a66",
            curved: false
          },
          "包括": {
            thickness: "weight",
            caption: true,
            color: "#ad1a66",
            curved: false
          },
          "对象": {
            thickness: "weight",
            caption: true,
            color: "#ad1a66",
          }
        },
        // initial_cypher:"match (n:"+this.label+")-[r:包括]->(n1) return n1,n,r",
        // initial_cypher:'MATCH (n:shipfhmh)-[r:包括] ->(n1) RETURN n1,n,r LIMIT 25',
        // initial_cypher:'MATCH (n:shiptecdm)-[r:包括] ->(n1) RETURN n1,n,r LIMIT 25',
        initial_cypher:'MATCH p=()-[r:`对象`]->() RETURN p LIMIT 25',
        initial_zoom:4.5,
        height:1800,
        width:1800,
        // initial_cypher:"MATCH p=(n:"+this.label+")-[r:`使用`]->() RETURN p LIMIT 25",
        // vis: {
        //   nodes: {
        //     shape: "circle", // 设置节点形状为圆形
        //     color: "#ad1a66", // 设置节点颜色为浅红色
        //     size: 500, // 设置节点大小为500
        //     label: { // 配置节点标签字体样式
        //       color: "#ffffff",
        //       size: 14,
        //       // 设置标签名称为节点的name属性
        //       p: "name"
        //     }
        //   },
        //   edges: {
        //     color: "#777", // 设置关系颜色为灰色
        //     thickness: "weight", // 设置关系粗细为weight属性值
        //     length: 100, // 设置关系长度为100
        //     label: { // 配置关系标签字体样式
        //       color: "#777",
        //       size: 12,
        //       // 在关系上展示关系权重的值
        //       field: "weight"
        //     }
        //   },
        //   physics: {
        //     barnesHut: {
        //       gravitationalConstant: -15000,
        //       springLength: 400
        //     },
        //     minVelocity: 0.75
        //   }
        // }
      };
      // eslint-disable-next-line no-undef
      this.viz = new NeoVis.default(config);
      this.viz.render();
      console.log(this.viz);
    },
    changeTab(){
      this.spin = true;
    }
  },
  mounted() {
    this.draw();
  }
}
</script>

<style scoped>

</style>