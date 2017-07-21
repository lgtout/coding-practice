import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder

// Assert that one collection contains the same items as another, with
// the same cardinality.
assertThat(listOf(1,2), containsInAnyOrder(*(listOf(2,1).toTypedArray())))