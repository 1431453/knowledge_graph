<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <span style="font-size: 20px;color: white">军事知识图谱自动构建与智能分析系统</span>
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <left-nav :getPageKey="getPageKey"></left-nav>
      </a-layout-sider>
      <a-layout style="padding: 16px 24px 24px">
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, maxHeight: '520px',overflow: 'auto' }"
        >
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script>
import { defineComponent, ref } from 'vue';
import LeftNav from "@/components/leftNav";
export default defineComponent({
  components: {
    LeftNav,
  },
  setup() {
    const result = ref('');
    const clickEven=(val)=>{
      console.log(val);
      result.value=val.content
    }
    return {
      clickEven,
      selectedKeys1: ref(['2']),
      selectedKeys2: ref(['1']),
      collapsed: ref(false),
      openKeys: ref(['sub1']),
    };
  },
  methods:{
    getPageKey(key){
      switch (JSON.parse(key)){
        case 1:
          this.$router.push('/three-search-content')
          break
        case 2:
          this.$router.push('/qa-search')
          break
        case 3:
          this.$router.push('/all-entity')
          break
        case 4:
          this.$router.push('/entity-tree')
              break
        case 5:
          this.$router.push('/graph-show')
              break
        case 6:
          this.$router.push('/test')
          break
        default:
          this.$router.push('/empty-page')
      }
    }
  }
});
</script>
<style scoped>
 .logo {
  float: left;
  width: 70px;
  height: 100%;
  margin: 1px 24px 1px 0;
  background-image: url("../assets/logo.png");
 background-repeat: no-repeat;
 background-position: center center;
 background-size: cover;
}
</style>