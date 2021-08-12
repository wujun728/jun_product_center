<template>
  <div :style="style" class="p-avatar">
    <table>
      <tbody>
        <tr>
          <td v-if="!hasImage">{{ initials }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import "./index.scss";
import { computed } from "vue";

export default{
  name: "p-avatar",
  props: {
    fullname: { type: String, default: "" },
    size: { type: Number, default: 48 },
    radius: {
      type: Number,
      default: 50,
      validator: (value) => value >= 0 && value <= 50,
    },
    color: { type: String, default: "#00c58e" },
    image: { type: String, default: "" },
  },
  setup(props) {

    const initials = computed(function () {
      const words = props.fullname.split(/[\s-]+/);
      return words
        .map((word) => word.substr(0, 1))
        .join("")
        .substr(0, 3)
        .toUpperCase();
    });
    const style = computed(function(){
        const fontSize = initials.value.length > 2 ? props.size / 3 : props.size / 2;
        return {
        width: props.size + "px",
        height: props.size + "px",
        "border-radius": props.radius + "%",
        "font-size": fontSize + "px",
        "background-color":
        props.color === "" ? toColor(props.fullname) : props.color,
        "background-image": hasImage.value ? "url(" + props.image + ")" : "none",
      };
    })
    const hasImage = computed(function(){
         return props.image !== "";
    })
    const toColor = function(str){
        let hash = 0;
      if (!str) return "black";
      for (const char of str.split("")) {
        hash = (hash << (8 - hash)) + char.charCodeAt(0);
      }
      return "#" + Math.abs(hash).toString(16).substr(0, 6);
    }
    return {
      initials,style,hasImage,toColor
    };
  }
};
</script>