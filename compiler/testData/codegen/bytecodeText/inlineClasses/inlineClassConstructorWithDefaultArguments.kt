// !LANGUAGE: +InlineClasses
// This tests both KT-37013 and KT-37015.

inline class A(val s: String = "OK")

// @A.class:
// 1 private synthetic <init>\(Ljava/lang/String;\)V
// 1 public static constructor-impl\(Ljava/lang/String;\)Ljava/lang/String;
// 1 public static synthetic constructor-impl\$default\(Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;\)Ljava/lang/String;
