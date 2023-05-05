<template>
  <div>
    <div>
      <a-radio-group v-model:value="value1" button-style="solid">
        <a-radio-button
            v-for="theme in themes"
            :key="theme.id"
            :value="theme.name"
            class="titleButton"
            @click="getTheme(theme)"
        >
          {{theme.title}}
        </a-radio-button>

      </a-radio-group>
    </div>
    <div style="margin-left: 60px;margin-top: 25px">
      <a-card :title="showTheme.title" style="width: 800px">
        <template #extra><a href="#">总计：{{entities.length}}</a></template>
        <a-tag
            v-for="item in entities"
            :key="item.id"
            :color="item.color"
            style="margin-left: 35px;width: 90px;text-align: center;margin-top: 10px"
        >
          {{item.title}}
        </a-tag>
      </a-card>
    </div>
  </div>
</template>
<script>
import { defineComponent, ref } from 'vue';
import apiGetAllEntity from "@/api/labelName";
export default defineComponent({
  setup() {
    const value1 = ref('position');
    const  themes= ref([
          {id:'001',title:'位置',name:'position',color:'pink'},
          {id:'002',title:'身份名词',name:'person',color:'blue'},
          {id:'003',title:'舰艇损管',name:'shipdm',color:'red'},
          {id:'004',title:'潜水相关',name:'diveeq',color:'orange'},
          {id:'005',title:'海洋相关',name:'searelated',color:'green'},
          {id:'006',title:'文件',name:'file',color:'cyan'},
          {id:'007',title:'化学名词',name:'chemistry',color:'pink'},
          {id:'008',title:'物理名词',name:'physics',color:'blue'},
          {id:'009',title:'人体组织',name:'body',color:'purple'},
          {id:'010',title:'常见属性',name:'attribute',color:'red'},
          {id:'011',title:'舰艇相关',name:'shipeq',color:'cyan'},
          {id:'012',title:'医学名词',name:'medical',color:'green'},
        ]);
    const showTheme = ref({id:'001',title:'位置',name:'position',color:'pink'});
    const entities = ref([]);
    const tagColor = ref('');
    return {
      showTheme,
      themes,
      value1,
      entities,
      tagColor,
    };
  },
  methods:{
    getTheme(theme){
      this.showTheme = theme;
      apiGetAllEntity(theme).then(res => {
        this.entities = res.data
        for(let item of this.entities){
          item['color'] = this.showTheme.color
        }
      }).catch((error)=>{
        console.log(error)
      })
    },
  },
  mounted() {
    this.getTheme(this.showTheme);
  }
});
</script>
<style>
.titleButton{
  width: 100px;
  margin-top: 10px;
  margin-left: 60px;
  text-align: center;
}
</style>