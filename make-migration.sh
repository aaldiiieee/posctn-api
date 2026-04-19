#!/bin/bash

CHANGELOG_DIR="src/main/resources/db/changelog"
MASTER_FILE="${CHANGELOG_DIR}/db.changelog-master.yaml"
EXT="sql"

LOCATION_FLAG=$1
TITLE=$2

# Validasi input
if [ -z "$LOCATION_FLAG" ] || [ -z "$TITLE" ]; then
  echo "❌ Usage:"
  echo "./make_migrate.sh --schema create_user_table"
  echo "./make_migrate.sh --data init_user"
  echo "./make_migrate.sh --changes add_email_to_user"
  echo "./make_migrate.sh --rollback rollback_user"
  exit 1
fi

# Tentukan subfolder
case "$LOCATION_FLAG" in
  --schema)
    SUB_DIR="schema"
    ;;
  --data)
    SUB_DIR="data"
    ;;
  --changes)
    SUB_DIR="changes"
    ;;
  --rollback)
    SUB_DIR="rollback"
    ;;
  *)
    echo "❌ Flag tidak dikenal"
    exit 1
    ;;
esac

# Generate timestamp
TIMESTAMP=$(date +"%Y%m%d%H%M%S")

# File path
TARGET_DIR="${CHANGELOG_DIR}/${SUB_DIR}"
mkdir -p "$TARGET_DIR"

FILENAME="${TIMESTAMP}_${TITLE}.${EXT}"
FULL_PATH="${TARGET_DIR}/${FILENAME}"

# Get git us
GIT_USER=$(git config user.name 2>/dev/null)
[ -z "$GIT_USER" ] && GIT_USER=$(whoami)

# Generate file
cat > "$FULL_PATH" <<EOF
-- liquibase formatted sql

-- changeset ${GIT_USER}:${TIMESTAMP}
-- description: ${TITLE}

-- TODO: tuliskan SQL migration di bawah ini

EOF

echo "✅ Migration dibuat: $FULL_PATH"

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

# Generate master changelog jika belum ada
#if [ ! -f "$MASTER_FILE" ]; then
#cat > "$MASTER_FILE" <<EOF
#databaseChangeLog:
#  - includeAll:
#      path: classpath:db/changelog/schema/
#  - includeAll:
#      path: classpath:db/changelog/data/
#  - includeAll:
#      path: classpath:db/changelog/changes/
#EOF
#
#echo "🆕 Master changelog dibuat dengan includeAll"
#fi