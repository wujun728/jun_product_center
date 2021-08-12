// imageUpload
const toolbar = [
  "imageUpload bold italic underline strikethrough table | alignleft aligncenter alignright alignjustify \
   | bullist numlist forecolor backcolor fontsizeselect  "
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
  toolbar_mode: "sliding",
  theme_advanced_resizing: false,
  menubar: false,
  branding: false,
  resize: true
};
export default init;
