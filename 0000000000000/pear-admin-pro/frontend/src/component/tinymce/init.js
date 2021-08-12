// imageUpload
const toolbar = [
  "imageUpload bold italic underline strikethrough table | alignleft aligncenter alignright alignjustify ltr rtl \
    | cut  copy  paste | bullist numlist outdent indent",
  "undo redo  styleselect  formatselect  fontselect  fontsizeselect forecolor backcolor removeformat fileUpload print baiduMap preview code fullscreen"
];
const plugins = [
  "print preview searchreplace autolink directionality visualblocks visualchars fullscreen link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount imagetools textpattern help autosave  autoresize "
];
let init = {
  language_url: require("@/assets/tinymce/langs/zh_CN.js"),
  language: "zh_CN",
  skin_url: require("@/assets/tinymce/skins/ui/oxide/skin.css"),
  min_height: 300,
  plugins: plugins,
  toolbar: toolbar,
  toolbar_drawer: "scrolling",
  toolbar_mode: "scrolling",
  theme_advanced_resizing: false,
  menubar: false,
  branding: false,
  resize: true
};
export default init;
