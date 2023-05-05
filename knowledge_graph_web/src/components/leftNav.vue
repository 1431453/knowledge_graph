<template>
    <a-layout-sider
        breakpoint="lg"
        collapsed-width="0"
        @collapse="onCollapse"
        @breakpoint="onBreakpoint"
        style="min-height: 550px"
    >
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item v-for="item in menus" :key="item.id" >
          <branches-outlined v-if="item.id === 1" aria-selected="true"/>
          <comment-outlined v-else-if="item.id === 2"/>
          <tags-outlined v-else-if="item.id === 3"/>
          <cluster-outlined v-else-if="item.id === 4"/>
          <deployment-unit-outlined v-else/>
          {{item.name}}
        </a-menu-item>
      </a-menu>
</a-layout-sider>
</template>
<script>
import { BranchesOutlined,CommentOutlined,TagsOutlined,DeploymentUnitOutlined,ClusterOutlined} from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';
export default defineComponent({
  components: {
    BranchesOutlined,
    CommentOutlined,
    TagsOutlined,
    DeploymentUnitOutlined,
    ClusterOutlined,
  },
  props:['getPageKey'],
  setup() {
    const menus = ref([
        {id:1,name:'元组检索'},
        {id:2,name:'问答检索'},
        {id:3,name:'实体分析'},
        {id:4,name:'树型结构'},
        {id:5,name:'图谱展示'},
        // {id:6,name:'testpage'},
    ]);
    const clickKeys = ref();
    const selectedKeys = ref([menus.value[0].id]);
    const onCollapse = (collapsed, type) => {
      console.log(collapsed, type);
    };

    const onBreakpoint = (broken) => {
      console.log(broken);
    };
    return {
      menus,
      selectedKeys,
      onCollapse,
      onBreakpoint,
      clickKeys,
    };
  },
  watch:{
    "selectedKeys"(newVal) {
      // console.log(`新值：${newVal}`);
      // console.log(`旧值：${oldVal}`);
      this.clickKeys = newVal;
      this.sendMenuKey();
    }
  },
  methods:{
    sendMenuKey(){
      console.log(this.clickKeys);
      this.getPageKey(this.clickKeys);
    }
  }
});
</script>
<style>
#components-layout-demo-responsive .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

[data-theme='dark'] .site-layout-sub-header-background {
  background: #141414;
}
</style>
