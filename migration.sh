#!/bin/bash

CHANGELOG_DIR="src/main/resources/db/changelog"
MASTER_FILE="${CHANGELOG_DIR}/db.changelog-master.yaml"
TITLE=$1
EXT="sql"

if [ -z "$TITLE" ]; then
  echo "❌ Error: Judul migration belum diberikan."
  echo "Usage: ./make_migrate.sh nama_migration"
  exit 1
fi

TIMESTAMP=$(date +"%Y%m%d%H%M%S")
FILENAME="${CHANGELOG_DIR}/${TIMESTAMP}_${TITLE}.${EXT}"
BASENAME="$(basename "$FILENAME")"
RELATIVE_PATH="db/changelog/${BASENAME}"

GIT_USER=$(git config user.name 2>/dev/null)
if [ -z "$GIT_USER" ]; then
  GIT_USER=$(whoami)
fi

mkdir -p "$CHANGELOG_DIR"

cat > "$FILENAME" <<EOF
-- liquibase formatted sql

-- changeset ${GIT_USER}:${TIMESTAMP}
-- preconditions onFail:MARK_RAN

-- TODO: tuliskan SQL migration di bawah ini

EOF

echo "✅ File migration berhasil dibuat: $FILENAME"

if [ ! -f "$MASTER_FILE" ]; then
  echo "databaseChangeLog:" > "$MASTER_FILE"
  echo "🆕 File master changelog dibuat: $MASTER_FILE"
fi

if grep -q "$RELATIVE_PATH" "$MASTER_FILE"; then
  echo "ℹ️  Migration sudah terdaftar di master changelog."
else
  {
    echo "  - include:"
    echo "      file: ${RELATIVE_PATH}"
  } >> "$MASTER_FILE"
  echo "✅ Migration ditambahkan ke master changelog: $MASTER_FILE"
fi
