<template>
  <div class="map-container">
    <my-map :zoom="5" :center="[106.011022, 37.237037]" height="100%">
        <my-map-geo :json="geo" :zIndex="1" :stroke="stroke" :fill="fill" :text="text"></my-map-geo>
        <my-map-layers placement="right-bottom" :layers="layers" :margin="10"></my-map-layers>
        <my-map-overview></my-map-overview>
        <my-map-pointer placement="right-top"></my-map-pointer>
        <my-map-scale></my-map-scale>
        <my-map-zoom></my-map-zoom>
        <my-map-circle :center="[116.261999, 36.130592]" :radius="50000" fill="#ff0000" :stroke="{width:1, color:'#000'}"></my-map-circle>
        <my-map-popup :position="[111.261999, 34.660592]" title="标题">
        主体内容
        </my-map-popup>
        <my-map-icon :position="[111.261999, 33.430592]" :size="40" name='el-icon-location'></my-map-icon>
        <my-map-line :coordinates="coordinates"></my-map-line>
        <my-map-link :from="[112.510186, 23.667921]" :to="[113.677856, 23.277534]"></my-map-link>
        <my-map-polygon :coordinates="line"></my-map-polygon>
        <my-map-scatter :coordinate="[112.561999, 23.130592]"></my-map-scatter>
        <my-map-flight :from="[116.566478, 40.513797]"
                    :to="[113.261999, 23.130592]"
                    :radius="10"
                    :angle="-120"
                    :space="0.1"
                    arrow></my-map-flight>
        <my-map-placement placement="left-top" style="padding: 10px;margin-left:40px">
          <el-button type="primary" size="small" :disabled="mode==='draw'" @click="draw">绘画模式</el-button>
          <el-button type="success" size="small" :disabled="mode==='modify'" @click="modify">编辑模式</el-button>
          <el-button type="info" size="small" :disabled="mode==='finish'" @click="finish">结束</el-button>
          <el-button type="warning" size="small" @click="getFeatures">获取图形</el-button>
          <el-button size="small" type="primary" @click="save">保存</el-button>
          <el-button size="small" @click="clear">清空</el-button>
        </my-map-placement>
        <my-map-draw ref="draw" @ready="handleReady" manual></my-map-draw>
    </my-map>
  </div>
</template>

<script>
  import {MyMap,MyMapLayers,MyMapOverview,MyMapPointer,MyMapScale,MyMapZoom,MyMapCircle,
  MyMapIcon,MyMapLine,MyMapLink,MyMapPolygon,MyMapPopup,MyMapScatter,MyMapFlight,MyMapPlacement,MyMapDraw,MyMapGeo} from '$ui/map'
  import {save, get, LOCAL} from '$ui/utils/storage'
  import Baidu from '$ui/map/sources/preview/Baidu.png'
  import Amap from '$ui/map/sources/preview/Amap.png'
  import OSM from '$ui/map/sources/preview/OSM.png'
  import TDT from '$ui/map/sources/preview/TDT.png'
  import china from '$ui/charts/geo/china.json'

  const cacheKey = '__draw_feature__'

  export default {
    components: {
      MyMap,
      MyMapLayers,
      MyMapOverview,
      MyMapPointer,
      MyMapScale,
      MyMapZoom,
      MyMapCircle,
      MyMapIcon,
      MyMapLine,
      MyMapLink,
      MyMapPolygon,
      MyMapPopup,
      MyMapScatter,
      MyMapFlight,
      MyMapPlacement,
      MyMapDraw,
      MyMapGeo
    },
    data() {
      return {
        layers: [
          {
            title: '百度',
            adapter: 'Baidu',
            preview: Baidu
          },
          {
            title: '高德',
            adapter: 'Amap',
            preview: Amap
          },
          {
            title: 'OSM',
            adapter: 'OSM',
            preview: OSM
          },
          {
            title: '天地图',
            adapter: 'TDT',
            preview: TDT
          }
        ],
        coordinates: [
          [112.810186, 23.267921],
          [113.577856, 23.277534],
          [113.164495, 23.009742],
          [113.811415, 22.958749]
        ],
        line: [[130, 30], [136, 35], [140, 33], [140, 30]],
        mode: 'finish',
        geo: china,
        stroke: {
          width: 1,
          color: '#000'
        },
        fill: 'rgba(0, 0, 0, 0)',
        text: {
          fill: 'green',
          font: '24px'
        }

      }
    },
    methods: {
      handleReady() {
        const json = get(cacheKey, LOCAL)
        if (json) {
          this.$refs.draw.fromJSON(json)
        }
      },
      draw() {
        this.$refs.draw.finish()
        this.$refs.draw.draw()
        this.mode = 'draw'
      },
      modify() {
        this.$refs.draw.finish()
        this.$refs.draw.modify()
        this.mode = 'modify'
      },
      finish() {
        this.$refs.draw.finish()
        this.mode = 'finish'
      },
      getFeatures() {
        const features = this.$refs.draw.getFeatures()
        alert('当前有' + features.length + '个图形')
      },
      clear() {
        this.$refs.draw.clear()
        this.save()
      },
      save() {
        this.$nextTick(() => {
          const json = this.$refs.draw.toJSON()
          save(cacheKey, json, LOCAL)
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
    .map-container{
        padding: 10px;
        height: calc(100vh - 84px);
    }
</style>
