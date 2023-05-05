<template>
    <a-row>
      <a-col :span="12" :offset="6">
        <a-input-search
            style="width:500px;"
            v-model:value="searchValue"
            placeholder="请输入你的问题"
            enter-button="Search"
            size="large"
            @search="onSearch"
        />
      </a-col>
    </a-row>
  <a-row>
    <a-col :span="18" :offset="2">
      <a-card title="检索结果" style="width: 100%; margin-top: 20px;height: 400px">
        <template #extra><question-circle-two-tone /></template>
        <div>
          <a-empty :description="null" v-if="result === undefined"></a-empty>
          <p
              v-else
              v-for="item in result"
              :key="item.index"
              style="margin-top: 15px;color: rgb(87,156,223);white-space: pre-line;"
          >
            <span v-if="result.length>1">（{{item.index}}）</span> {{item.value}}<br  style="line-height: 1.6em;">
          </p>
        </div>
      </a-card>
    </a-col>
  </a-row>
  </template>
  <script>
  import {QuestionCircleTwoTone} from "@ant-design/icons-vue";
  import { defineComponent, ref } from 'vue';
  import apiGetAnswer from '../api/qaRoute'
  export default defineComponent({
    components:{
      QuestionCircleTwoTone
    },
    setup() {
      const searchValue = ref('');
      const result = ref();
      function onSearch(){
        let question = {'title': searchValue.value}
        result.value = [];
        apiGetAnswer(question).then(res => {
          console.log(res.data)
          res.data.forEach(function (value,index){
            result.value.push({'index':index,'value':value})
          })
          console.log(result.value)
        }).catch((error)=>{
          console.log(error)
        })
      }
      return {
        searchValue,
        result,
        onSearch,
      };
    },
  });
  </script>
  
  