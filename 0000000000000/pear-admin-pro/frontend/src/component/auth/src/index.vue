<template>
    <template v-if="hasAuthority">
        <slot></slot>
    </template>
</template>

<script>
import { computed, defineComponent } from "vue";
import { useStore } from 'vuex';

export default defineComponent({
	name: 'pro-authority',
	props: {
		value: {
			type: [String, Boolean],
			default: false
		},
	},
	setup(props) {
		const store = useStore();
		const hasAuthority = computed(() => {
			if(props.value == false) return true;
			return store.getters.power.indexOf(props.value) != -1;
		});
		return {
			hasAuthority
		};
	},
});
</script>