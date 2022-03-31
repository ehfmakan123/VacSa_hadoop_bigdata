<template>
  <b-row>
    <b-col cols="12" md="6">
      <BasicDropdown />
    </b-col>
    <b-col cols="12" md="6">
      <SliptDropdown />
    </b-col>
    <b-col cols="12">
      <DropdownSize />
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "User",

  data: () => ({
    page: {
      title: "User",
    },
  }),
  components: {
    BasicDropdown: () => import("@/components/ui/dropdown/BasicDropdown"),
    SliptDropdown: () => import("@/components/ui/dropdown/SliptDropdown"),
    DropdownSize: () => import("@/components/ui/dropdown/DropdownSize"),
  },
};
</script>
