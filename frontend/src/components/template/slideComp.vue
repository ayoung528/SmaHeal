<template>
    <v-slide-item>
        <v-card
            class="ma-4"
            width="300"
            light
            >
            <v-fab-transition>
                <v-btn
                color="white"
                fab
                small
                absolute
                top
                left
                icon
                >
                <v-icon>mdi-emoticon-excited</v-icon>
                </v-btn>
            </v-fab-transition>
            <v-img
                class="white--text align-end"
                height="250px"
                :src="require(`../../../public/images/${item.photo}`)"
            >
            </v-img>
            <v-card-title>{{item.userId}}
                <v-spacer></v-spacer>
                <v-card-actions>
                <v-btn
                icon
                @click="show = !show"
                >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
            </v-card-actions>

            <v-expand-transition>
                <div v-show="show">
                <v-card-text>
                    {{item.comment}}
                </v-card-text>
                </div>
            </v-expand-transition>
            </v-card-title>
        </v-card>
    </v-slide-item>
</template>

<script>
export default {
    name: "slideComp",
    props: {
        propsitem: {type: Object}
    },
    data: function() {
        return {
            item: this.propsitem,
            show: false,
            showArrows: true,
        }
    }
}
</script>