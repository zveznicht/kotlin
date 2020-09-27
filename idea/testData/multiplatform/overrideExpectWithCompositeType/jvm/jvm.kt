actual typealias Expect = String

interface Derived : Base {
    override fun expectInReturnType(): Box<Expect>

    <!NOTHING_TO_OVERRIDE!>override<!> fun expectInArgument(e: Box<Expect>)

    <!NOTHING_TO_OVERRIDE!>override<!> fun Box<Expect>.expectInReceiver()

    override val expectVal: Box<Expect>

    override var expectVar: Box<Expect>
}

/**
 * Questions:
 *
 * - Why expect in other places aside from return type doesn't lead to error?
 *   - Data Point: NOTHING_TO_OVERRIDE should be reported in such case, in checkOverridesForMemberMarkedOverride, and there have been
 *     kotlinTypeRefiner wired into findInvisibleOverriddenDescriptor
 *   - However, it seems like even if 'findInvisibleOverriddenDescriptor' binds overrides correctly, we should report
 *     CANNOT_OVERRIDE_INVISIBLE_MEMBER instead. We don't do it. Why?
 *     - Because earlier we bind overrides correctly in 'org.jetbrains.kotlin.resolve.OverridingUtil.createAndBindFakeOverride', so
 *       'overriddenDescriptors' is not empty
 *     - In other words, we don't pull usual (visible) overrides from that refiner
 *   - Then why we needed refiner in 'findInvisibleOverriddenDescriptor'?
 *     - Likely, because otherwise we won't be able to report error on invisible overrides
 *   - So, basically, error on return type is special in that regard, as it reported separately, by separate checks, rather than
 *     during generic overrides bindingg process (which works correctly with refiners)
 *  Case solved!
 *
 *  - Do we have other checks like one for return type?
 *  - It seems like overrideResolver calls 'isSubtypeOf' more frequently than just for return type. Does it lead to issues? If no, then why?
 *    - Yes, for properties return type only (and that seems to be it, because we don't see any other usages of KotlinTypeChecker in OverrideResolver)
 *
 * - Some methods in overrideResolver are static, and called in other clients, in particular, ImplementMembersHandler. Prove that it
 *   leads to issues indeed. Fix it by finding correct refiner.
 *   - Surpisingly, it doesn't lead to issues. Left the comment
 *
 * - Why we saw issues with nullability in IDE? Why we don't see them in tests? (or maybe we do)? What are correct invariants w.r.t.
 *   refining nullable types?
 *      - Data Point: NullableSimpleType doesn't override 'hasNonTrivialRefinementFactory', which means that it works through 'replaceDelegate'
 *        and is never meant to be cached, thus it shouldn't be necessary to reapply nullability.
 *        However, the issue with 'cancel' was definitely fixed by reapplying nullability
 *
 * - Finally, even if we call refine type everywhere in OverrideResolver (and OverrideUtil) correctly, it seems that it won't work with
 *   composite types (because they should be refined by TypeChecker)
 *      - This leads to issue with buildString
 *      - Data Point: Only NewKotlinTypeChecker able to refine types, and OverridingUtil uses KotlinTypeCheckerImpl. How should we approach that?
 */