import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

import { init } from "./out/test.app";

export default class App extends React.Component {
  constructor() {
    super();

    this.root = null;
  }

  componentDidMount() {
    init(this);
  }

  swapRoot(newRoot) {
    this.root = newRoot;
    this.forceUpdate();
  }

  render() {
    if (this.root) {
        return this.root;
    } else {
        return (
          <View style={styles.container}>
            <Text>No Root.</Text>
          </View>
        );
    }
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
