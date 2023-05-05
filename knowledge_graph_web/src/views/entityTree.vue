<template>
  <div>
    <a-radio-group v-model:value="mode" :style="{ marginBottom: '8px' }">
      <a-radio-button style="width:210px;margin-left: 20px;font-size: 16px ">章节标题</a-radio-button>
<!--      <a-radio-button style="width:600px;font-size: 16px">树形结构</a-radio-button>-->
      <a-input-search
          v-model:value="searchValue"
          placeholder="请输入想要查询的值"
          enter-button
          @search="onSearch"
          style="margin-left: 240px;margin-top: -32px"
      />
    </a-radio-group>
    <a-tabs
        v-model:activeKey="activeKey"
        :tab-position="mode"
        :style="{ height: '100%' }"
        @tabScroll="callback"
        @change="onChange"
    >
      <a-tab-pane v-for="item in themes" :key="item.id" :tab="item.title">
        <div style="max-height: 480px; overflow:auto;margin-left: 40px">
          <a-tree
              v-model:selectedKeys="selectedKeys"
              v-model:expandedKeys="expandedKeys"
              :tree-data="treeData1"
              show-icon
              :expanded-keys="expandedKeys"
          >
            <template #icon="{ key }">
              <template v-if="key === '0-0'">
                <radar-chart-outlined />
              </template>
              <template v-else-if="key === '0-0-0' || key === '0-0-0-0'">
                <branches-outlined />
              </template>
            </template>
            <template #title="{ title }">
              <span v-if="title.indexOf(searchValue) > -1">
                {{ title.substr(0, title.indexOf(searchValue)) }}
                <span style="color: #1890ff;font-size: 15px">{{ searchValue }}</span>
                {{ title.substr(title.indexOf(searchValue) + searchValue.length) }}
              </span>
              <span v-else>{{ title }}</span>
            </template>
          </a-tree>
        </div>

      </a-tab-pane>
    </a-tabs>
  </div>
</template>
<script>
import { RadarChartOutlined,BranchesOutlined} from "@ant-design/icons-vue";

import {defineComponent, ref, watch} from 'vue';
export default defineComponent({
  components: {
    // DownOutlined,
    BranchesOutlined,
    RadarChartOutlined,
  },
  setup() {
    const searchValue = ref('');
    const  themes= ref([
      {id:'001',title:'第一章：舰艇防火灭火',name:'shipfhmh',color:'red'},
      {id:'002',title:'第二章：舰艇技术装备损管',name:'shiptecdm',color:'blue'},
      {id:'003',title:'第三章：舰艇抗沉',name:'shipresunk',color:'orange'},
      {id:'004',title:'第四章：舰艇损管组织与训练',name:'shipdmorgandwork',color:'green'},
      {id:'005',title:'第五章：潜水疾病',name:'divemedical',color:'cyan'},
      {id:'006',title:'第六章：潜水技术与基础训练',name:'divetecwork',color:'pink'},
      {id:'007',title:'第七章：潜水相关与潜水器具',name:'divereandeq',color:'blue'},
      {id:'008',title:'第八章：潜水作业与组织实施',name:'diveorganduse',color:'purple'},
      {id:'009',title:'第九章：潜水物理知识',name:'divephysics',color:'red'},
    ]);
    const expandedKeys = ref(['0-0-0']);
    const selectedKeys = ref([]);
    const treeData = [{
      title: 'parent 1',
      key: '0-0',
      children: [{
        title: 'parent 1-0',
        key: '0-0-0',
        children: [{
          title: 'leaf',
          key: '0-0-0-0',
        }, {
          title: 'leaf',
          key: '0-0-0-1',
        }, {
          title: 'leaf',
          key: '0-0-0-2',
        }],
      }, {
        title: 'parent 1-1',
        key: '0-0-1',
        children: [{
          title: 'leaf',
          key: '0-0-1-0',
        }],
      }, {
        title: 'parent 1-2',
        key: '0-0-2',
        children: [{
          title: 'leaf',
          key: '0-0-2-0',
        }, {
          title: 'leaf',
          key: '0-0-2-1',
        }],
      }],
    }];
    const treeData1 = [
      {
        title: '舰艇火灾',
        key: '0-0',
        children: [
          {
            title: 'A类火灾',
            key: '0-0-0',
            children: [
              {title: '1A级灭火剂', key: '0-0-0-0'},
              {title: '2A级灭火剂', key: '0-0-0-1'},
              {title: '3A级灭火剂', key: '0-0-0-2'},
              {title: '4A级灭火剂', key: '0-0-0-3'},
              {title: '6A级灭火剂', key: '0-0-0-4'}]
          },
          {
            title: 'B类火灾', key: '0-0-1',
            children: [
              {title: '2B级灭火剂', key: '0-0-1-0'},
              {title: '3B级灭火剂', key: '0-0-1-1'},
              {title: '4B级灭火剂', key: '0-0-1-2'},
              {title: '5B级灭火剂', key: '0-0-1-3'},
              {title: '9B级灭火剂', key: '0-0-1-4'},
              {title: '12B级灭火剂', key: '0-0-1-5'},
              {title: '14B级灭火剂', key: '0-0-1-6'},
              {title: '22B级灭火剂', key: '0-0-1-7'}]
          },
          {title: 'C类火灾', key: '0-0-2'},
          {title: 'D类火灾', key: '0-0-3'},
          {title: 'E类火灾', key: '0-0-4'},
          {title: '弹药火灾', key: '0-0-5'},
          {
            title: '燃烧', key: '0-0-6',
            children: [
              {title: '闪燃', key: '0-0-6-0'},
              {title: '闪点', key: '0-0-6-1'},
              {title: '可燃液体', key: '0-0-6-2'},
              {title: '甲类液体', key: '0-0-6-3'},
              {title: '乙类液体', key: '0-0-6-4'},
              {title: '丙类液体', key: '0-0-6-5'},
              {title: '着火', key: '0-0-6-6'},
              {title: '燃点', key: '0-0-6-7'},
              {title: '自燃', key: '0-0-6-8'},
              {title: '本身自燃', key: '0-0-6-9'},
              {title: '受热自燃', key: '0-0-6-10'}]
          },
          {
            title: '爆炸', key: '0-0-7',
            children: [
              {title: '物理爆炸', key: '0-0-7-0'},
              {title: '化学爆炸', key: '0-0-7-1'},
              {title: '爆炸极限', key: '0-0-7-2'},
              {title: '爆炸上限', key: '0-0-7-3'},
              {title: '爆炸下限', key: '0-0-7-4'}]
          },
          {
            title: '有害气体', key: '0-0-8',
            children: [
              {title: '烟', key: '0-0-8-0'}]
          },
          {
            title: '灭火原理', key: '0-0-9',
            children: [
              {title: '冷却法', key: '0-0-9-0'},
              {title: '窒息法', key: '0-0-9-1'},
              {title: '隔离法', key: '0-0-9-2'},
              {title: '化学抑制作用灭火', key: '0-0-9-3'}]
          },
          {
            title: '火灾热扩散', key: '0-0-10',
            children: [
              {title: '热传导', key: '0-0-10-0'},
              {title: '热辐射', key: '0-0-10-1'},
              {title: '对流', key: '0-0-10-2'},
              {title: '对流换热', key: '0-0-10-3'}]
          }
        ],
      }
    ];

    const treeData2 = [{
      title: '舰艇技术装备损管',
      key: '0-0',
      children: [
          {
        title: '舰艇技术装备',
        key: '0-0-0',
        children: [
          {
          title: '动力装置',
          key: '0-0-0-0',
          children: [
              {title: '战斗使用原则', key: '0-0-0-0-0'},
              {title: '战斗使用要求', key: '0-0-0-0-1'},
              {title: '战斗使用计划', key: '0-0-0-0-2'},
              {title: '损害管制计划', key: '0-0-0-0-3'}]},
              {
          title: '管路破损修复',
          key: '0-0-0-1',
          children: [{title: '管路用材料包扎', key: '0-0-0-1-0'}, {title: '管路用工具修理', key: '0-0-0-1-1'}]
          },
          {
            title: '管路包扎器材修理',
            key: '0-0-0-1-2',
            children: [{
              title: '管路包扎器材',
              key: '0-0-0-1-2-0',
              children: [{
                title: '普通管箍',
                key: '0-0-0-1-2-0-0'
              }, {
                title: '带铰链管箍',
                key: '0-0-0-1-2-0-1',
                children: [{
                  title: '单铰链管箍',
                  key: '0-0-0-1-2-0-1-0'
                }, {
                  title: '双铰链管箍',
                  key: '0-0-0-1-2-0-1-1'
                }]
              }, {
                title: '带尾伸缩管箍',
                key: '0-0-0-1-2-0-2'
              }, {
                title: '低压伸缩管箍',
                key: '0-0-0-1-2-0-3'
              }, {
                title: '带形管箍',
                key: '0-0-0-1-2-0-4',
                children: [{
                  title: '固定带形管箍',
                  key: '0-0-0-1-2-0-4-0'
                }, {
                  title: '活动带形管箍',
                  key: '0-0-0-1-2-0-4-1'
                }]
              }, {
                title: '链条管箍',
                key: '0-0-0-1-2-0-5'
              }, {
                title: '管口盖',
                key: '0-0-0-1-2-0-6',
                children: [{
                  title: '螺栓管口盖',
                  key: '0-0-0-1-2-0-6-0'
                }, {
                  title: '撑架管口盖',
                  key: '0-0-0-1-2-0-6-1'
                }]
              }, {
                title: '不锈钢管道堵漏器',
                key: '0-0-0-1-2-0-7'
              }, {
                title: '链条管钳',
                key: '0-0-0-1-2-0-8'
              }, {
                title: '制式管路包扎器材',
                key: '0-0-0-1-2-0-9',
                children: [{
                  title: '管道堵漏器',
                  key: '0-0-0-1-2-0-9-0'
                }, {
                  title: '链条管钳和半圆钢管包扎装置',
                  key: '0-0-0-1-2-0-9-1'
                }, {
                  title: '管路快速包扎装置',
                  key: '0-0-0-1-2-0-9-2'
                }]
              }]
            }]
          }]
        },
        {
          title: '蒸气管路破损',
          key: '0-0-0-2',
          children: [{
            title: '修理蒸气管路破损',
            key: '0-0-0-2-0',
            children: [{
              title: '低压蒸气管小破损的缠扎法',
              key: '0-0-0-2-0-0'
            }, {
              title: '中、高压蒸气管小破损的管箍法',
              key: '0-0-0-2-0-1'
            }, {
              title: '高压蒸气管严重破损的管口盖堵塞法',
              key: '0-0-0-2-0-2',
              children: [{
                title: '简易管口盖堵塞法',
                key: '0-0-0-2-0-2-0'
              }, {
                title: '管口盖和“C”形夹堵塞法',
                key: '0-0-0-2-0-2-1'
              }, {
                title: '螺栓管口盖堵塞法',
                key: '0-0-0-2-0-2-2'
              }, {
                title: '撑架管口盖堵塞法',
                key: '0-0-0-2-0-2-3'
              }]
            }, {
              title: '破损蒸气管焊补法',
              key: '0-0-0-2-0-3'
            }, {
              title: '更换法兰盘垫料的方法',
              key: '0-0-0-2-0-4'
            }]
          }]
        },
        {
          title: '油、水管路破损',
          key: '0-0-0-3',
          children: [{
            title: '修理油水管路破损',
            key: '0-0-0-3-0',
            children: [{
              title: '接管法',
              key: '0-0-0-3-0-0'
            },
              {
              title: '用木塞、木楔、管箍和铁丝等修理低压管路小破口',
              key: '0-0-0-3-0-1'
            },
              {
              title: '破损管路焊补法',
              key: '0-0-0-3-0-2'
            },
              {
              title: '用螺丝铆钉堵塞窄的裂缝的方法',
              key: '0-0-0-3-0-3'
            },
              {
              title: '缠扎法',
              key: '0-0-0-3-0-4'
            },
              {
              title: '更换法兰盘垫料的方法',
              key: '0-0-0-3-0-5'
            },
              {
              title: '利用环氧树脂修复管道破损',
              key: '0-0-0-3-0-6'
            },
              {
              title: '制作木楔和木塞堵塞管上的细小破损',
              key: '0-0-0-3-0-7'
            }]
          }]
        },
      ],
        }];
    const mode = ref('left');
    const activeKey = ref(themes.value[0].id);
    const callback = val => {
      console.log(val);
    };
    watch(searchValue, value => {
      // const expanded = dataList.map(item => {
      //   if (item.title.indexOf(value) > -1) {
      //     return getParentKey(item.key, gData.value);
      //   }
      //   return null;
      // }).filter((item, i, self) => item && self.indexOf(item) === i);
      expandedKeys.value = ['0-0-0-3-0','0-0-0','0-0','0-0-1','0-0-2','0-0-3','0-0-3','0-0-4','0-0-5','0-0-6','0-0-7','0-0-0-1','0-0-0-2','0-0-0-3'];
      searchValue.value = value;
      // autoExpandParent.value = true;
    });
    return {
      searchValue,
      themes,
      mode,
      callback,
      activeKey,
      treeData1,
      treeData2,
      expandedKeys,
      selectedKeys,
      treeData,
    };
  },
  methods:{
    getIcon(depth) {
      const iconMap = {
        0: 'smile',
        1: 'meh',
        2: 'frown',
      };
      return iconMap[depth] || 'smile';
    },
    customTitle({ title, node }) {
      return (
          <span>
          <a-icon type={this.getIcon(node.level)} style="margin-right: 8px;"></a-icon>
          <span>{title}</span>
        </span>
      );
    },
    onSearch(){

    },
    onChange(){
      this.treeData1 = this.treeData2
    }
  }
});
</script>