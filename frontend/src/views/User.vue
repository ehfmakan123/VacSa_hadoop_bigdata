<template>
  <b-row>
    <b-col cols="12" md="6" xl="4" class="d-flex align-items-stretch">
      <AvatarImageContent />
    </b-col>
    <b-col cols="12" md="6" xl="4" class="d-flex align-items-stretch">
      <AvatarIconContent />
    </b-col>
    <b-col cols="12" md="6" xl="4" class="d-flex align-items-stretch">
      <AvatarTextContent />
    </b-col>
    <b-col cols="12" md="6" xl="4" class="d-flex align-items-stretch">
      <AvatarMultilineText />
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "Avatar",

  data: () => ({
    page: {
      title: "Avatar",
    },
  }),
  components: {
    AvatarImageContent: () => import("@/components/user/AvatarImageContent"),
    AvatarIconContent: () => import("@/components/user/AvatarIconContent"),
    AvatarTextContent: () => import("@/components/user/AvatarTextContent"),

    AvatarMultilineText: () => import("@/components/user/AvatarMultilineText"),
  },
};
</script>
