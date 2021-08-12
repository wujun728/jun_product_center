import Aside from "./aside";
import Avatar from "./avatar";
import Button from "./button";
import ButtonGroup from "./button-group";
import ButtonContainer from "./button-container";
import CheckBox from "./checkbox";
import CheckBoxGroup from "./checkbox-group";
import Container from "./container";
import Count from "./count";
import Card from "./card";
import Col from "./col";
import Footer from "./footer";
import Header from "./header";
import Icon from "./icon";
import Input from "./input";
import Switch from "./switch";
import Slider from "./slider";
import InputNumber from "./input-number";
import InputSearch from "./input-search";
import Row from "./row";
import Radio from "./radio";
import RadioGroup from "./radio-group";
import Main from "./main";

const components = [
    Aside,Avatar,
    Button,ButtonGroup,ButtonContainer,Main,InputSearch,Input,
    Container,CheckBox,CheckBoxGroup,Card,Col,Footer,Header,Icon,Switch,Slider,Count,InputNumber,Row,Radio,RadioGroup
]

const install = function (Vue) {
    if (install.installed) return
    components.map(component => Vue.component(component.name, component))
}

export default{ 
     install,
     Aside,
     Avatar,
     Button,
     ButtonGroup,
     ButtonContainer,
     Col,
     Card,
     Count,
     Container,
     CheckBox,
     CheckBoxGroup,
     Icon,
     Footer,
     Header,
     Switch,
     Slider,
     InputNumber,
     InputSearch,
     Input,
     Row,
     Radio,
     RadioGroup,
     Main
}

import "./theme/index.scss"