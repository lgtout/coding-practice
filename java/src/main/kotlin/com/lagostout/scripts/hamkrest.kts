import com.natpryce.hamkrest.assertion.assertThat

// Assert that one collection contains the same items as another, with
// the same cardinality.
assertThat(listOf(1,2), Collection<Int>::containsAll, listOf(2,1))