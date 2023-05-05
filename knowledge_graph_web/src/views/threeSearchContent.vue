<template>
    <a-space direction="vertical">
      <a-input style="width: 700px" v-model:value="searchMessage.entity">
        <template #addonBefore>
          <a-select v-model:value="searchMessage.label" style="width: 160px">
            <a-select-option v-for="item in themes" :key="item.id" :value="item.name">{{item.title}}</a-select-option>
          </a-select>
        </template>
      </a-input>
      <a-input style="width: 700px" v-model:value="searchMessage.relation">
        <template #addonBefore>
          <a-select v-model:value="value2" style="width: 160px">
            <a-select-option v-for="item in about" :key="item.id" :value="item.name">{{item.name}}</a-select-option>
          </a-select>
        </template>
      </a-input>
      <a-input style="width: 700px" v-model:value="searchResult">
        <template #addonBefore>
          <a-select v-model:value="value4" style="width: 160px" disabled>
            <a-select-option v-for="item in about" :key="item.id" :value="value4">{{value4}}</a-select-option>
          </a-select>
        </template>
      </a-input>
      <a-button type="primary" ghost style="width: 150px" @click="onSearch">检索</a-button>
    </a-space>
  </template>
  <script>
  import {  } from '@ant-design/icons-vue';
  import {defineComponent, reactive, ref} from 'vue';
  import apiGetThreeSearch from "@/api/threeSearch";
  export default defineComponent({
    components: {
    },
    setup() {
      const value1 = ref('选择实体');
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
      const about = ref([{id: 1, name:'关系'},{id:2,name:'属性'}]);
      const result = ref([{id: 1, name:'关系结果'},{id:2,name:'属性值'}]);
      const value2 = ref(about.value[0].name);
      const value4 = ref(result.value[0].name);
      const searchResult = ref('');
      const searchMessage = reactive({
        label:'选择实体',
        entity:'',
        relation:'',
      })
      const resultId = ref(0);
      return {
        value1,
        value2,
        resultId,
        value4,
        themes,
        about,
        result,
        searchMessage,
        searchResult,
      };
    },
    watch:{
      "value2"(newVal){
        console.log(newVal)
        this.about.forEach(item =>{
          if(item.name === newVal){
            console.log(item.id);
            this.resultId = item.id;
            console.log(this.resultId)
            this.value4 = this.result[this.resultId-1].name
            console.log(this.value4)
          }
        })
      }
    },
    methods:{
      onSearch(){
        console.log(this.searchMessage)
        apiGetThreeSearch(this.searchMessage).then(res => {
          console.log(res.data)
          this.searchResult = res.data[0]
          console.log(this.value4)
        }).catch((error)=>{
          console.log(error)
        })
      }
    }
  });
  </script>